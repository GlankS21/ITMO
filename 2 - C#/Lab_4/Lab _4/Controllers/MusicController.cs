using Lab_4.Models;
using Lab_4.Repositories;
using Lab_4.Serviecs;
using Microsoft.AspNetCore.Mvc;

namespace Lab_4.Controllers;

[ApiController]
public class MusicController:ControllerBase {
    private readonly IMusicRepository _musicRepository;

    public MusicController(IMusicRepository musicRepository) {
        _musicRepository = musicRepository;
    }
   
    
    // add new music to the catalog
    [HttpPost("/add-new-music")]
    public Task Add(string author, string composition) {
        var _music = new MusicModel() { 
            author = author,
            composition = composition,
            Id = Guid.NewGuid()
        };
        return _musicRepository.AddMusic(_music);
    }
       
    //Get all music from catalog 
    [HttpGet("/get-all-musics")]
    public Task<List<MusicModel>> GetAll() {
        return Task.FromResult(_musicRepository.GetAll().Result);
    }
    
    //Get music by part of name (composition name)
    [HttpGet("get-by-part-of-composition-name {PartOfName}")]
    public Task<List<MusicModel>> GetByPartOfName(string PartOfName) {
        return Task.FromResult(_musicRepository.FindByPartOfName(PartOfName).Result);
    }
    
    //Delete music
    [HttpDelete("delete-music {FullNameOfMusic}")]
    public Task DeleteMusic(string FullNameOfMusic) {
        return _musicRepository.DeleteMusic(FullNameOfMusic);
    }

    [HttpGet("get-by-id {Id}")]
    public Task<List<MusicModel>> GetById(string Id) {
        return Task.FromResult(_musicRepository.FindById(Id).Result);
    }
}