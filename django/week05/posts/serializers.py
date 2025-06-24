from rest_framework.serializers import ModelSerializer
from .models import Post, Comment

class PostModelSerializer(ModelSerializer):
    class Meta:
        model = Post
        fields = '__all__' # 모든 필드를 직렬화
        #fields = ['id', 'writer', 'content'] # 특정 필드만 직렬화

class PostListSerializer(PostModelSerializer):
    pass

class PostRetrieveSerializer(PostModelSerializer):
    class Meta(PostModelSerializer.Meta):
        depth = 1


class CommentListModelSerializer(ModelSerializer):
    class Meta:
        model = Comment
        fields = '__all__'