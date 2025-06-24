from django.contrib import admin
from .models import Post, Comment

class CommentInline(admin.TabularInline): 
    model = Comment
    extra = 3
    fields = ['content', 'writer', 'created_at']
    readonly_fields = ['created_at']  

@admin.register(Post)
class PostAdmin(admin.ModelAdmin):
    list_display = ['id', 'writer', 'content', 'created_at', 'view_count']
    inlines = [CommentInline] 
