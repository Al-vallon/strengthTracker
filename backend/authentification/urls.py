from django.urls import path
from .views import RegisterView, LoginView, LogoutView, UserListView
from rest_framework_simplejwt.views import TokenRefreshView
from .views import ProtectedView

urlpatterns = [
    path('register/', RegisterView.as_view(), name='register'),
    path('login/', LoginView.as_view(), name='login'),
    path('protected/', ProtectedView.as_view(), name='protected'),
    path('token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('logout/', LogoutView.as_view(), name='logout'),
    path('users/', UserListView.as_view(), name='users'),
]