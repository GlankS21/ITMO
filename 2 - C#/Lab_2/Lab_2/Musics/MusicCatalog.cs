using Lab_2.Musics;

namespace Lab_2;

public class MusicCatalog: IMusicCatalog {
    private List<Music> _musics = new();
    
    public List<Music> listMusic() {
        Console.WriteLine("All compositions in catalog:");
        foreach (var _music in _musics) {
            Console.WriteLine(_music.getMusic());   
        }
        return _musics; 
    }

    public bool seachMusic(string name) {
        List<Music> resultMusic = new List<Music>();
        foreach (var _music in _musics) {
            if(_music.compositionName.Contains(name))
                resultMusic.Add(_music);
        }

        if (resultMusic.Count == 0) {
            Console.WriteLine("No one item was found by this criteria.");
            return false;
        }
        Console.WriteLine("Results found:");
        foreach (var _music in resultMusic) {
            Console.WriteLine(_music.getMusic());
        }
        return true;
    }

    public void addMusic(Music music) {
        _musics.Add(music);
    }

    public void deleteMusic(string name) {
        var find = false;
        for (var i = 0; i < _musics.Count; i++) {
            if (_musics[i].getMusic() == name) {
                Console.WriteLine($"Track '{name}' deleted.");
                _musics.RemoveAt(i);
                find = true;
            }
        }
        if(!find) Console.WriteLine("Music not found");
    }
}