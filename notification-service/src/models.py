from datetime import datetime

from pydantic import BaseModel


class ShipmentEmailView(BaseModel):
    """The shipment fields used to compose a delivery notification."""

    tracking_number: str
    order_id: str
    estimated_delivery_at: datetime
