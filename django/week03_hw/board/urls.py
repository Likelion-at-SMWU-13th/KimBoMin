from django.urls import path
from .views import (
    BoardListView, BoardDetailView,
    BoardCreateView, BoardUpdateView, BoardDeleteView
)

app_name = 'board'

urlpatterns = [
    path('', BoardListView.as_view(), name='list'),
    path('new/', BoardCreateView.as_view(), name='create'),
    path('<int:pk>/', BoardDetailView.as_view(), name='detail'),
    path('<int:pk>/edit/', BoardUpdateView.as_view(), name='edit'),
    path('<int:pk>/delete/', BoardDeleteView.as_view(), name='delete'),
]
