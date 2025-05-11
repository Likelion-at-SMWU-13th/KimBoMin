from django.contrib import admin
from django.urls import path, include
from posts.views import url_view, url_parameter_view, function_view, class_view
from posts.views import index
from django.conf import settings
from django.conf.urls.static import static

urlpatterns = [
    path('admin/', admin.site.urls),
    path('url/', url_view),
    path('url/<str:username>/', url_parameter_view),
    path('fbv/', function_view),
    path('cbv/', class_view.as_view()),

    path('', index, name='index'),
    path('posts/', include('posts.urls', namespace='posts')),
    path('accounts/', include('accounts.urls', namespace='accounts')),

    path('board/', include('board.urls', namespace='board')),
]

if settings.DEBUG:
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
