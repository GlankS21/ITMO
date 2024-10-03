using System.Collections.Generic;
using Lab_3.Model;
using Lab_3.Musics;

namespace Lab_3.Core;
 
public interface IMusicRepository {
    //вывести информацию обо всех существующих в каталоге композициях
    public List<MusicModel> GetAll(); 
    //добавить информацию о композиции в каталог
    public void SetMusic(MusicModel music); 
    public List<Music> FindByPartOfName(string PartOfName); 
    public void DeleteMusic(string title); 
}