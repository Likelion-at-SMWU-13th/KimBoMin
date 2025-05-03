from django.contrib import admin
from .models import Diary

@admin.register(Diary)
class DiaryAdmin(admin.ModelAdmin):
    list_display = ['id', 'title', 'created_at']  
    list_filter = ['created_at']                 
    search_fields = ['title', 'content']         
    search_help_text = '제목과 내용으로 검색'  
