using System.ComponentModel.DataAnnotations;

namespace Lab_4.Models;

public class MusicModel {
    public string author { get; set; }
    public string composition{ get; set; }  
     
    [Key] 
    public Guid Id { get; set; }
}  