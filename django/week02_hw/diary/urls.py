from django.urls import path
from .views import DiaryListView

urlpatterns = [
    path('diary/', DiaryListView.as_view(), name='diary-list'),
]
