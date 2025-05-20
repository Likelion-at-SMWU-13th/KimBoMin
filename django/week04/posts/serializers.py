from rest_framework.serializers import ModelSerializer
from .models import Post

class PostModelSerializer(ModelSerializer):
    class Meta:
        model = Post
        fields = '__all__' # 모든 필드를 직렬화
        #fields = ['id', 'writer', 'content'] # 특정 필드만 직렬화