import sqlalchemy

from .db_session import SqlAlchemyBase


class StoreEntity(SqlAlchemyBase):
    __tablename__ = 'stores'

    id = sqlalchemy.Column(sqlalchemy.Integer, primary_key=True, autoincrement=True)
    location = sqlalchemy.Column(sqlalchemy.String, nullable=True)
    region = sqlalchemy.Column(sqlalchemy.String, nullable=True)
    name = sqlalchemy.Column(sqlalchemy.String, nullable=True)
    email = sqlalchemy.Column(sqlalchemy.String, default="vk@mail.ru")

    def __repr__(self):
        return f'<StoreEntity> {self.name}.\n"{self.email}"'
