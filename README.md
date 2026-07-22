# wirefit-services

Demo service repo wired to [wirefit](https://github.com/Wirefit/wirefit) contract
checking against [TieuLongHo/wirefit-contracts](https://github.com/TieuLongHo/wirefit-contracts).

| service | role | contract source |
|---|---|---|
| `inventory-service` | provider | `proto/stock.proto#StockLevel` (schema-native, no extractor) |
| `web-store` | consumer of `inventory.get-stock` | `src/StockView.ts` via `wirefit-ts` |
| `order-service` | provider | `OrderResponse` via `wirefit-java` and Maven |
| `web-app-ts` | consumer of `order-service`'s `orders.get-order` | `src/OrderView.ts` via `wirefit-ts` |
| `fulfillment-service` | consumer of `orders.get-order`; provider of `fulfillment.get-shipment` | Go structs via the built-in extractor |
| `notification-service` | consumer of `fulfillment.get-shipment` | Pydantic v2 model via `wirefit-py` |

CI ([contracts.yml](.github/workflows/contracts.yml)): every PR runs `wirefit check`
per service (breaking a consumed field blocks the merge with a sticky PR comment);
pushes to main additionally `wirefit publish` to the contracts repo, which re-renders
the [deploy matrix page](https://tieulongho.github.io/wirefit-contracts/).

## Deploy examples

The [deploy workflow](.github/workflows/deploy-contracts.yml) has three GitHub
environments, in promotion order: `dev` → `stg` → `prd`. It checks the release
candidate against the contract versions recorded in the target environment, then records
the deployed version after the deployment step. For a promotion, it checks the version
already recorded in the previous environment, so no new artifact is accidentally promoted.

Wirefit stores the stage order in the **contracts repository**. Add this once there (the
copyable example is [contracts-repo/_envs/pipeline.yaml](contracts-repo/_envs/pipeline.yaml)):

```yaml
schema-version: 1
envs: [dev, stg, prd]
```

`dev` deployments use the current checkout as the candidate. Select `dev` as the source
for a `dev` → `stg` promotion or `stg` for a `stg` → `prd` promotion. The workflow
contains the intentionally small `Deploy service` hook where the real deployment command
belongs; the contract gate and deploy record surround it.

Secrets: `CONTRACTS_REPO_TOKEN` needs read (PRs) / write (main) access to the
contracts repo. Currently a personal token for testing — replace with a
fine-grained PAT scoped to `contents: write` on `wirefit-contracts`.
