package CoorpMsg.controller;

import CoorpMsg.domain.Project;
import CoorpMsg.domain.Tasks;
import CoorpMsg.repos.ProjectRepo;
import CoorpMsg.repos.TasksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TaskController {
    @Autowired
    private TasksRepo tasksRepo;
    @Autowired
    private ProjectRepo projectRepo;

    @GetMapping("/addTask/{id}")
    public String addTask(@PathVariable("id") Integer id,Map<String, Object> model ){
        Project project = projectRepo.findById(id);
        model.put("project", project);
        return "/addTasks";
    }


    @PostMapping("/addTask/{id}")
    public String add(@PathVariable("id") Integer id,
                      @RequestParam String deadline,
                      @RequestParam String description) {
        Tasks tasks = new Tasks(id, description, deadline);
        tasksRepo.save(tasks);
        return "redirect:/projects/" + id;
    }

    @GetMapping("/tasks/{id}")
    public String editTask (@PathVariable("id") Integer id, Map<String, Object> model ) {
        Tasks task = tasksRepo.findById(id);
        model.put("task", task);
        return "task";
    }

    @PostMapping ("/tasks/{id}")
    public String editpostTask(
            @RequestParam String stage,
            @RequestParam String deadline,
            @RequestParam String description,
            @PathVariable("id") Integer id){
        Tasks task = tasksRepo.findById(id);
        task.setDescription(description);
        task.setDeadline(deadline);
        task.setStage(stage);
        tasksRepo.save(task);
        return "redirect:/projects/" + task.getIdProj();
    }

    @GetMapping("/deleteTask/{id}")
    public String userDelete(@PathVariable("id") Integer id, Map<String, Object> model){
        Tasks task = tasksRepo.findById(id);
        model.put("task", task);
        return"/deleteTask";
    }

    @PostMapping ("/deleteTask/{id}")
    public String userDelete (  @PathVariable("id") Integer id){
        Tasks task = tasksRepo.findById(id);
        tasksRepo.delete(task);
        return "redirect:/projects/" + task.getIdProj();
    }
}
