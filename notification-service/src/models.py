from datetime import datetime
from typing import Literal
from uuid import UUID

from pydantic import BaseModel


class InvoiceEmailView(BaseModel):
    """The billing event fields used to compose an invoice email."""

    amountCents: int
    createdAt: datetime
    currency: str
    customerEmail: str
    invoiceId: UUID
    orderId: UUID
    status: Literal["DRAFT", "ISSUED", "PAID", "VOID"]
