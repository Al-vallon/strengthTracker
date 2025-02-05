from django.db import models

# Create your models here.
from django.contrib.auth.models import AbstractUser, Group, Permission, BaseUserManager
from django.db import models

from django.contrib.auth.models import BaseUserManager

class UserManager(BaseUserManager):
    def create_user(self, email, username, password=None, **extra_fields):
        if not email:
            raise ValueError("L'utilisateur doit avoir une adresse email")
        if not username:
            raise ValueError("L'utilisateur doit avoir un nom d'utilisateur")
        
        email = self.normalize_email(email)
        user = self.model(email=email, username=username, **extra_fields)
        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, email, username, password=None, **extra_fields):
        extra_fields.setdefault("is_staff", True)
        extra_fields.setdefault("is_superuser", True)

        return self.create_user(email, username, password, **extra_fields)


class User(AbstractUser):
    email = models.EmailField(unique=True)
    created_at = models.DateTimeField(auto_now_add=True)
    modified_at = models.DateTimeField(auto_now=True)
    """Modèle utilisateur personnalisé"""
    groups = models.ManyToManyField(
        Group,
        related_name="custom_user_set",  # Évite le conflit avec auth.User
        blank=True
    )
    user_permissions = models.ManyToManyField(
        Permission,
        related_name="custom_user_permissions_set",  # Évite le conflit avec auth.User
        blank=True
    )
