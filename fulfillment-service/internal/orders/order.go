package orders

// OrderView is the subset of an order required to allocate a shipment.
type OrderView struct {
	OrderID    string `json:"order_id"`
	Status     string `json:"status"`
	TotalCents int64  `json:"total_cents"`
}
