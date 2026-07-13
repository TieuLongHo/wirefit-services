// web-store's view of inventory.get-stock (proto3 JSON names, camelCase).
// A small projection: removing a proto field web-store doesn't read here is
// a safe change, exactly like the order-service story.
export interface StockView {
  sku: string;
  availableUnits: number;
  backorderable: boolean;
}
