using Microsoft.EntityFrameworkCore;
using Lab_3.Model;

namespace Lab_3;

public class MusicCatalogContext:DbContext
{
    public DbSet<MusicModel> Musics { get; set; }
    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder) {
        optionsBuilder.UseSqlite("Data Source = Lab_3.db");
    }
} 