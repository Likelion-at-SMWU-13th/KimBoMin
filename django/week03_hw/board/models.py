from django.db import models
from django.contrib.auth import get_user_model

User = get_user_model()

class BoardPost(models.Model):
    title = models.CharField(max_length=100)
    content = models.TextField()
    image = models.ImageField(upload_to='board/', null=True, blank=True)
    created_at = models.DateTimeField(auto_now_add=True)
    writer = models.ForeignKey(User, on_delete=models.CASCADE)

    def __str__(self):
        return self.title
