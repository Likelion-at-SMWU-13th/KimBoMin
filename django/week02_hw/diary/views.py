from django.shortcuts import render
from .models import Diary
from django.views.generic import ListView

class DiaryListView(ListView):
    model = Diary
    template_name='diary/diary_list.html'

# Create your views here.
