using Lab_4.DB;
using Lab_4.Models;
using Lab_4.Serviecs;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace Lab_4.Repositories;

public class MusicRepository:IMusicRepository {
    private readonly MusicCatalogContext _dbContext;
    public MusicRepository(MusicCatalogContext dbContext) {
        _dbContext = dbContext;
    }

    public async Task<List<MusicModel>> GetAll() { 
        if (_dbContext.Musics == null) return new List<MusicModel>();
        return await _dbContext.Musics.ToListAsync();
    }

    // list 
    public Task AddMusic(MusicModel music) {  
        _dbContext.Musics.Add(music); 
        return _dbContext.SaveChangesAsync(); 
    }

    //searchByAuthor
    public async Task<List<MusicModel>> FindByPartOfName(string PartOfName) {
        var musics = await GetAll();
        return new List<MusicModel>(musics.Where(m => m.composition.Contains(PartOfName)));
    }

    public async Task<List<MusicModel>> FindById(string id)
    {
        var musics = await GetAll();
        return new List<MusicModel>(musics.Where(m => m.Id == Guid.Parse(id)));
    }

    //delete
    public Task DeleteMusic(string title) {
        var elements = title.Split("-");
        MusicModel music = _dbContext.Musics.FirstOrDefault(music => music.author == elements[0] && music.composition == elements[1]);
        _dbContext.Remove(music);
        return _dbContext.SaveChangesAsync();
    }
}