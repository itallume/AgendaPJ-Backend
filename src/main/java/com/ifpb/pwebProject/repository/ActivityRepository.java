package com.ifpb.pwebProject.repository;

import com.ifpb.pwebProject.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {
}
