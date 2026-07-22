package api

import "time"

// ShipmentView is the response from GET /shipments/{tracking_number}.
type ShipmentView struct {
	TrackingNumber      string    `json:"tracking_number"`
	OrderID             string    `json:"order_id"`
	EstimatedDeliveryAt time.Time `json:"estimated_delivery_at"`
}
