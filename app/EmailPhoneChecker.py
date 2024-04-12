from phonenumbers import is_valid_number, parse
import re


def validate_login(login):
    if len(login) > 7:
        if re.match(r'\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,}\b', login) is not None:
            print(f'{login} is email')
            return "email"
    if is_valid_number(parse(login, "RU")):
        print(f'{login} is phone')
        return "phone"
    print(f'{login} is invalid')
    return False