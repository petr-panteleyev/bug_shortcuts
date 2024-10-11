# Unexpected accelerators behavior

1. Set JAVA_HOME and PATH to jdk-23
2. Build and Launch \
```mvn clean package exec:exec```
3. Press buttons: Ctrl+], Ctrl+[, Ctrl+\\ \
All keys are reflected in label content.
4. Comment Option 1 and uncomment Option 2.
5. Now Ctrl+\ does not work
