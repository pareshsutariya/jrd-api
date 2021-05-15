package net.xitiz.jrd.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.xitiz.jrd.entities.Ticket;
import net.xitiz.jrd.repositories.TicketRepository;

@RestController
public class TicketRestController {

    @Autowired
    TicketRepository repo;

    @GetMapping("/api/tickets")
    public List<Ticket> findAll() {
        return repo.findAll();
    }

    @GetMapping("/api/tickets/{id}")
    public Ticket findById(@PathVariable int id) throws Exception {
        var entity = repo.findById(id);
        if (!entity.isPresent())
            throw new Exception("Ticket not found for the given id : " + id);

        return entity.get();
    }

    @PostMapping("/api/tickets")
    public Ticket post(@RequestBody Ticket ticket) {
        return repo.save(ticket);
    }

    @PutMapping("/api/tickets/{id}")
    public Ticket put(@PathVariable int id, @RequestBody Ticket ticket) {
        return repo.save(ticket);
    }

    @DeleteMapping("/api/tickets/{id}") 
    public void delete(@PathVariable int id) throws Exception {
        var item = repo.findById(id);
        
        if (!item.isPresent())
            throw new Exception("Ticket not found for the given id : " + id);

        repo.deleteById(id);
    }
}