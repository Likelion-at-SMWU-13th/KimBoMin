from django.urls import path
from .views import (
    post_list_create_view,
    post_detail_view,
    post_update_view,
    post_delete_view,

    user_create_view,
    user_detail_view,
    user_update_view,
    user_delete_view,
)

urlpatterns = [
    
    path('posts/', post_list_create_view),
    path('posts/<int:pk>/', post_detail_view),
    path('posts/<int:pk>/edit/', post_update_view),
    path('posts/<int:pk>/delete/', post_delete_view),

    
    path('users/', user_create_view),
    path('users/<int:pk>/', user_detail_view),  
    path('users/<int:pk>/edit/', user_update_view),
    path('users/<int:pk>/delete/', user_delete_view),
]
