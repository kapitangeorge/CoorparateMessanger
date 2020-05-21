package CoorpMsg.controller;

import CoorpMsg.domain.Project;
import CoorpMsg.domain.Tasks;
import CoorpMsg.domain.User;
import CoorpMsg.repos.ProjectRepo;
import CoorpMsg.repos.TasksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ProjectController {
    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private TasksRepo tasksRepo;

    @GetMapping("/projects")
    public String project(Map<String, Object> model){
        Iterable<Project> project = projectRepo.findAll();
        model.put("project", project);
        return "projects";
    }

    @PostMapping("/projects")
    public String addProject(@AuthenticationPrincipal User user,
                             @RequestParam String name,
                             @RequestParam String deadline,
                             @RequestParam String description,
                             Map<String, Object> model){
        Project project = new Project(name, user, deadline, description);
        projectRepo.save(project);
        return project(model);
    }

    @GetMapping("/projects/{id}")
    public String getById (@PathVariable("id") Integer id, Map<String, Object> model ) {
        Project project = projectRepo.findById(id);
        Iterable<Tasks> tasks = tasksRepo.findByIdProj(id);

        model.put("project", project);
        model.put("tasks", tasks);
        return "projectsForm";
    }

    @GetMapping("/editProject/{id}")
    public String editProject (@PathVariable("id") Integer id, Map<String, Object> model ) {
        Project project = projectRepo.findById(id);
        model.put("project", project);
        return "editProject";
    }

    @PostMapping ("/editProject/{id}")
    public String editpostProject(
                                  @RequestParam String name,
                                  @RequestParam String deadline,
                                  @RequestParam String description,
                                  @PathVariable("id") Integer id){
        Project project = projectRepo.findById(id);
        project.setDescription(description);
        project.setDeadline(deadline);
        project.setName(name);
        projectRepo.save(project);
        return "redirect:/projects/" + id;
    }
    @GetMapping("/deleteProject/{id}")
    public String userDelete(@PathVariable("id") Integer id, Map<String, Object> model){
        Project project = projectRepo.findById(id);
        model.put("project", project);
        return"/deleteProject";
    }

    @PostMapping ("/deleteProject/{id}")
    public String userDelete (  @PathVariable("id") Integer id){
        Project project = projectRepo.findById(id);
        projectRepo.delete(project);
        return "redirect:/projects";
    }}


