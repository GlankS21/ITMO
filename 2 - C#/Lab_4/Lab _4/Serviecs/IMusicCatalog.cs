using Lab_4.Models;

namespace Lab_4.Serviecs;

public interface IMusicCatalog
{
    Task<List<MusicModel>> listMusic();  
    List<Music> seachMusic(string name);
    void addMusic(MusicModel music); 
    bool deleteMusic(string name);  
} 