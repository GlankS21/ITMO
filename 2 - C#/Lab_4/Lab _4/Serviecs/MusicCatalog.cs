using Lab_4.Models;
using Lab_4.Repositories;

namespace Lab_4.Serviecs;

public class MusicCatalog:IMusicCatalog
{
    private IMusicRepository _musics;
    public MusicCatalog(IMusicRepository musicRepository) {
        _musics = musicRepository;
    }
     
    
    public Task<List<MusicModel>> listMusic() {
        Console.WriteLine("All compositions in catalog:");
        return _musics.GetAll();
    }

    public List<Music> seachMusic(string PartOfName) {
        List<Music> resultMusic = _musics.FindByPartOfName(PartOfName).Result.Select(m => new Music(m.author, m.composition)).ToList();;
        
        if (resultMusic.Count == 0) 
            Console.WriteLine("No one item was found by this criteria.");
        else {
            Console.WriteLine("Results found:");
            foreach (var _music in resultMusic)
                Console.WriteLine(_music.getMusic());
        }

        return resultMusic;
    }

    public void addMusic(MusicModel music) {
        _musics.AddMusic(music);
    }
  
    public bool deleteMusic(string name) { 
        List<Music> musics = _musics.GetAll().Result.Select(m => {
            return new Music(m.author, m.composition);
        }).ToList(); 
          
        var find = false;
        for (var i = 0; i < musics.Count; i++) {
            if (musics[i].getMusic() == name) {
                Console.WriteLine($"Track '{name}' deleted.");
                _musics.DeleteMusic(name);
                return true;
            }
        }
        Console.WriteLine("Music not found");
        return false;
    } 
}