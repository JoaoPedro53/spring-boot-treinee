package developer.jota.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/heroes")
public class HeroContoller {
    private static final List<String> HEROES = List.of("Zoro", "Luffy", "Seya", "Sakamoto");

    @GetMapping()
    public List<String> listAll(){
        return HEROES;
    }

    @GetMapping("filter")
    public List<String> listAllHeroesParam(@RequestParam(required = false) String name){
        return HEROES.stream().filter(hero -> hero.equalsIgnoreCase(name)).toList();
    }

    @GetMapping("filterList")
    public List<String> listAllHeroesParamList(@RequestParam List<String> names){
        return HEROES.stream().filter(names::contains).toList();
    }

    @GetMapping("{name}")
    public String findByName(@PathVariable String name){
        return HEROES.stream().filter(name::equalsIgnoreCase)
                .findFirst()
                .orElse("");
    }

}
