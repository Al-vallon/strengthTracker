from django.contrib.auth.backends import BaseBackend
from .models import User

class EmailBackend(BaseBackend):
    def authenticate(self, request, username=None, password=None, **kwargs):
        try:
            # Rechercher un utilisateur avec l'email (username est l'email ici)
            user = User.objects.get(email=username)
            if user.check_password(password):
                return user
        except User.DoesNotExist:
            return None
