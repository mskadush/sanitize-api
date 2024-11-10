package com.example.sanitize.adapters.out.persistence.jpa.models

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SensitiveWordRepository : JpaRepository<SensitiveWordModel, String>
