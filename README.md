# aop-training

This repo contains some AOP concepts used to create custom annotations.

# Some AOP Concepts:

- Join point: a point during the execution of a program, such as the execution of a method or the handling of an
  exception.
- Pointcut: a set of one or more JoinPoint where an advice should be executed. Pointcuts help narrow down the join
  points advised by an aspect.
- Spring `@EnableAspectJAutoProxy` annotation enables support for handling components marked with @Aspect annotation of
  AspectJ.
- Advice is an action taken by an aspect at a particular join point. Different types of advice include “around,”
  “before” and “after” advice. The main purpose of aspects is to support cross-cutting concerns, such as logging,
  profiling, caching, and transaction management.

