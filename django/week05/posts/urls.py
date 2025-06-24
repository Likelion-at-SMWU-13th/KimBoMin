from django.urls import path, include
from django.contrib import admin
from rest_framework import routers

from .views import PostModelViewSet, PostListView, PostRetrieveView

app_name="posts"

router_post = routers.DefaultRouter()
router_post.register('', PostModelViewSet)

urlpatterns = [
    path('', include(router_post.urls)),
    path('', PostListView.as_view()),
    path('<int:pk>', PostRetrieveView.as_view()),
    path('admin/', admin.site.urls)
]