import json

import requests
from requests.structures import CaseInsensitiveDict

url = "http://localhost:8080/orders/place_order"
url1 = "http://localhost:8080/api/items"
url2 = "http://localhost:8080/api/auth/signup"


headers = CaseInsensitiveDict()
headers["Accept"] = "application/json"
headers["Content-Type"] = "application/json"


# resp = requests.post(url, headers=headers, data=data)

# resp = requests.get(url1)
# print(resp.status_code, resp.content)/

# data = '{"login": "79851173554", "password": "kasdjhvsauhewiviweb213"}'
# resp = requests.post(url2, data=data, headers=headers)
# print(resp.status_code, resp.content)
# headers["bearer"] = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImlhdCI6MTcwNjM2NzM5MiwibmJmIjoxNzA2MzY3MzkyLCJqdGkiOiJhYWU0YjNkMC04YWVlLTQzMGItOTg3Ni1mMmUzM2MxMzkwZmMiLCJleHAiOjE3MDYzNjgyOTIsInR5cGUiOiJhY2Nlc3MiLCJmcmVzaCI6ZmFsc2V9.qiG-dNegeYbjt7LOM3e4QUkWZMtPIuYYD-W2sjzDG4I"
# headers["refresh"] = ("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImlhdCI6MTcwNjM2NzM5MiwibmJmIjoxNzA2MzY3MzkyLCJqdGkiOiIxYTg2Y2FiMC03YWI0LTQ1MzktODUzYy1mMDNiNzk5YjhmNWYiLCJleHAiOjE3MDg5NTkzOTIsInR5cGUiOiJyZWZyZXNoIn0.p3MUupFtHzOT1uRhG4XSaE2BxAyLEjqpha7l4E9o7aY")

data = '{"login": "a132123@gmail.com", "password": "kasdjhvsauhewiviweb213"}'
resp = requests.post("https://b421-62-183-34-186.ngrok-free.app/api/auth/signup", data=data, headers=headers)
print(resp.status_code, json.loads(resp.content))




data = '{"login": "a132123@gmail.com", "password": "kasdjhvsauhewiviweb213"}'
resp = requests.post("https://b421-62-183-34-186.ngrok-free.app/api/auth/login", data=data, headers=headers)
a = json.loads(resp.content)
headers["Authorization"] = f'Bearer {a["access"]}'
print(a["access"])


print(resp.status_code, resp.content)
# resp = requests.post(url, data='{"content":{}}', headers=headers)
# print(resp.status_code, resp.content)