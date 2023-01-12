package com.sdl.turnkeyapp.repository;

import com.sdl.turnkeyapp.models.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepo extends JpaRepository<Issue, Integer> {
}
