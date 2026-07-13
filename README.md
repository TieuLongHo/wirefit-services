# wirefit-services

Demo service repo wired to [wirefit](https://github.com/Wirefit/wirefit) contract
checking against [TieuLongHo/wirefit-contracts](https://github.com/TieuLongHo/wirefit-contracts).

| service | role | contract source |
|---|---|---|
| `inventory-service` | provider | `proto/stock.proto#StockLevel` (schema-native, no extractor) |
| `web-store` | consumer of `inventory.get-stock` | `src/StockView.ts` via `wirefit-ts` |
| `web-app-ts` | consumer of `order-service`'s `orders.get-order` | `src/OrderView.ts` via `wirefit-ts` |

CI ([contracts.yml](.github/workflows/contracts.yml)): every PR runs `wirefit check`
per service (breaking a consumed field blocks the merge with a sticky PR comment);
pushes to main additionally `wirefit publish` to the contracts repo, which re-renders
the [deploy matrix page](https://tieulongho.github.io/wirefit-contracts/).

Secrets: `CONTRACTS_REPO_TOKEN` needs read (PRs) / write (main) access to the
contracts repo. Currently a personal token for testing — replace with a
fine-grained PAT scoped to `contents: write` on `wirefit-contracts`.
