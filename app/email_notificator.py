import base64
import os

from google.auth.transport.requests import Request
from google.oauth2.credentials import Credentials
from google_auth_oauthlib.flow import InstalledAppFlow
from googleapiclient.discovery import build
import pickle

class EmailNotificator:

    def __init__(self):

        self.service = gmail_authenticate()
        self.sender = 'your_email@gmail.com'

    def send_message(self, title, message, receivers):
        for to in receivers:
            message = create_message(self.sender, to, title, message)
            send_message(self.service, 'me', message)


SCOPES = ['https://www.googleapis.com/auth/gmail.send']


def gmail_authenticate():
    creds = None
    # the file token.pickle stores the user's access and refresh tokens, and is
    # created automatically when the authorization flow completes for the first time
    if os.path.exists("token.pickle"):
        with open("token.pickle", "rb") as token:
            creds = pickle.load(token)
    # if there are no (valid) credentials availablle, let the user log in.
    if not creds or not creds.valid:
        if creds and creds.expired and creds.refresh_token:
            creds.refresh(Request())
        else:
            flow = InstalledAppFlow.from_client_secrets_file('../credentials.json', SCOPES)
            creds = flow.run_local_server(port=0)
        # save the credentials for the next run
        with open("token.pickle", "wb") as token:
            pickle.dump(creds, token)
    return build('gmail', 'v1', credentials=creds)


def create_message(sender, to, subject, message_text):
    message = f"From: {sender}\nTo: {to}\nSubject: {subject}\n\n{message_text}"
    b64_message = base64.urlsafe_b64encode(message.encode('utf-8')).decode()
    return {'raw': b64_message}


def send_message(service, user_id, message):
    try:
        message = service.users().messages().send(userId=user_id, body=message).execute()
        print('Message Id: %s' % message['id'])
        return message
    except Exception as error:
        print(f'An error occurred: {error}')


if __name__ == '__main__':
    e = EmailNotificator()
