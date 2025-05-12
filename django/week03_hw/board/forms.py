from django import forms
from .models import BoardPost

class BoardPostForm(forms.ModelForm):
    class Meta:
        model = BoardPost
        fields = ['title', 'content', 'image']
