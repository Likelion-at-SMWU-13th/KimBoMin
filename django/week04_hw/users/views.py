from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status
from django.shortcuts import get_object_or_404
from django.contrib.auth import get_user_model

from .models import Post
from .serializers import PostSerializer, UserSerializer

User = get_user_model()


@api_view(['GET', 'POST'])
def post_list_create_view(request):
    if request.method == 'GET':
        posts = Post.objects.all()
        serializer = PostSerializer(posts, many=True)
        return Response(serializer.data)

    elif request.method == 'POST':
        serializer = PostSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response({'message': '글 작성 완료'}, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=400)

@api_view(['GET'])
def post_detail_view(request, pk):
    post = get_object_or_404(Post, pk=pk)
    serializer = PostSerializer(post)
    return Response(serializer.data)

@api_view(['PUT', 'PATCH'])
def post_update_view(request, pk):
    post = get_object_or_404(Post, pk=pk)
    serializer = PostSerializer(post, data=request.data, partial=(request.method == 'PATCH'))
    if serializer.is_valid():
        serializer.save()
        return Response({'message': '글 수정 완료'})
    return Response(serializer.errors, status=400)

@api_view(['DELETE'])
def post_delete_view(request, pk):
    post = get_object_or_404(Post, pk=pk)
    post.delete()
    return Response({'message': '글 삭제 완료'}, status=status.HTTP_204_NO_CONTENT)



@api_view(['POST'])
def user_create_view(request):
    serializer = UserSerializer(data=request.data)
    if serializer.is_valid():
        serializer.save()
        return Response({'message': '회원가입 완료'}, status=status.HTTP_201_CREATED)
    return Response(serializer.errors, status=400)

@api_view(['GET'])
def user_detail_view(request, pk):
    user = get_object_or_404(User, pk=pk)
    serializer = UserSerializer(user)
    return Response(serializer.data)

@api_view(['PUT', 'PATCH'])
def user_update_view(request, pk):
    user = get_object_or_404(User, pk=pk)
    serializer = UserSerializer(user, data=request.data, partial=(request.method == 'PATCH'))
    if serializer.is_valid():
        serializer.save()
        return Response({'message': '회원정보 수정 완료'})
    return Response(serializer.errors, status=400)

@api_view(['DELETE'])
def user_delete_view(request, pk):
    user = get_object_or_404(User, pk=pk)
    user.delete()
    return Response({'message': '회원 탈퇴 완료'}, status=status.HTTP_204_NO_CONTENT)
