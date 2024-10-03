using System.ComponentModel.DataAnnotations;

namespace Lab_3.Model;

public class MusicModel {
    public string author { get; set; }
    public string composition{ get; set; }
     
    [Key]
    public Guid Id { get; set; }
}