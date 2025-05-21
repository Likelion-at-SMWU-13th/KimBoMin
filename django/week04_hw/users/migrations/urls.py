from django.urls import path
from .views import (
    post_list_create_view,
    post_detail_view,
    post_update_view,
    post_delete_view
)

urlpatterns = [
    path('posts/', post_list_create_view),
    path('posts/<int:pk>/', post_detail_view),
    path('posts/<int:pk>/edit/', post_update_view),
    path('posts/<int:pk>/delete/', post_delete_view),
]
