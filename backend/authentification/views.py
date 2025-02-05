from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from rest_framework.permissions import AllowAny, IsAuthenticated
from rest_framework_simplejwt.tokens import RefreshToken
from django.contrib.auth import authenticate

from .models import User
from authentification.serializers import UserSerializer, RegisterSerializer

def get_tokens_for_user(user):
    """ Génère des tokens JWT pour un utilisateur """
    refresh = RefreshToken.for_user(user)
    return {
        'refresh': str(refresh),
        'access': str(refresh.access_token),
    }

class RegisterView(APIView):
    permission_classes = [AllowAny]

    def post(self, request):
        serializer = RegisterSerializer(data=request.data)
        if serializer.is_valid():
            user = serializer.save()
            # tokens = get_tokens_for_user(user)
            return Response({'user': UserSerializer(user).data}, status=status.HTTP_201_CREATED)
        print(serializer.errors)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class LoginView(APIView):
    permission_classes = [AllowAny]
    
    print(f"LoginView")

    def post(self, request):
        email = request.data.get('email')
        password = request.data.get('password')
        
        print(f"Email: {email}, Password: {password}")
        
        user = authenticate(request, username=email, password=password)
        
        print(f"User: {user}")
        
        if user:
            tokens = get_tokens_for_user(user)
            return Response({'tokens': tokens, 'user': UserSerializer(user).data})
        return Response({'error': 'Invalid Credentials'}, status=status.HTTP_400_BAD_REQUEST)
    
class LogoutView(APIView):
    permission_classes = [IsAuthenticated]

    def post(self, request):
        try:
            refresh_token = request.data.get("refresh")
            token = RefreshToken(refresh_token)
            token.blacklist()  # Ajoute le token à la blacklist
            return Response({"message": "Déconnexion réussie"}, status=status.HTTP_200_OK)
        except Exception as e:
            return Response({"error": "Token invalide ou déjà révoqué"}, status=status.HTTP_400_BAD_REQUEST)

class ProtectedView(APIView):
    """ Endpoint qui nécessite un JWT valide """
    permission_classes = [IsAuthenticated]

    def get(self, request):
        return Response({'message': f'Bonjour {request.user.first_name}, tu es authentifié !'})

class UserListView(APIView):
    permission_classes = [IsAuthenticated]
    def get(self, request):
        users = User.objects.all()
        serializer = UserSerializer(users, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)