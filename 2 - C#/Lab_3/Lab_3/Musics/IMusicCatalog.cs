using Lab_3.Model;

namespace Lab_3.Musics;

public interface IMusicCatalog
{
    List<Music> listMusic(); 
    List<Music> seachMusic(string name);
    void addMusic(MusicModel music);
    bool deleteMusic(string name);
} 