import sqlalchemy

from .db_session import SqlAlchemyBase


class PriceEntity(SqlAlchemyBase):
    __tablename__ = 'prices'

    id = sqlalchemy.Column(sqlalchemy.Integer, primary_key=True, autoincrement=True)
    name = sqlalchemy.Column(sqlalchemy.String, nullable=True)
    category = sqlalchemy.Column(sqlalchemy.String, nullable=True)
    price = sqlalchemy.Column(sqlalchemy.String, default="example@gmail.com")
    store = sqlalchemy.Column(sqlalchemy.Integer, default=0)
    strike = sqlalchemy.Column(sqlalchemy.Boolean, default=0)
    in_process = sqlalchemy.Column(sqlalchemy.Boolean, default=0)
    spam = sqlalchemy.Column(sqlalchemy.Boolean, default=0)
    user = sqlalchemy.Column(sqlalchemy.Integer, nullable=False)

    def __repr__(self):
        return f'<PriceEntity> {self.name} {self.category}.\n"{self.price}"'
