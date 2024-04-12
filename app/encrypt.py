import hashlib


def encrypt(__value, __salt):
    # hashing value
    __hashed_value = hashlib.pbkdf2_hmac('sha256', __value.encode('utf-8'), __salt, 10000)

    return __hashed_value
