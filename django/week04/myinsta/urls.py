from django.contrib import admin
from django.urls import path, include

from posts.views_cal import calculator_query

urlpatterns = [
    path('admin/', admin.site.urls),
    path('posts/', include('posts.urls', namespace='posts')),
    path{'calculater/query', calculator_query},
]
