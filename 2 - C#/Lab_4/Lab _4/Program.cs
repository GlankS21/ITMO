using Lab_4.DB;
using Lab_4.Repositories;
using Lab_4.Serviecs;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();


builder.Services.AddDbContext<MusicCatalogContext>(options => {
    options.UseSqlite(builder.Configuration.GetConnectionString("Lab_4"));
});
  
builder.Services.AddTransient<IMusicRepository, MusicRepository>();
builder.Services.AddTransient<IMusicCatalog, MusicCatalog>();

builder.Services.AddControllers(); 

var app = builder.Build(); 

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment()) 
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.MapControllers();


app.Run();