NODES = {
    "A": ("127.0.0.1", 5001),
    "B": ("127.0.0.1", 5002),
    "C": ("127.0.0.1", 5003),
    "D": ("127.0.0.1", 5004),
    "E": ("127.0.0.1", 5005),
}

CENTRAL_NODE = "A"

# Para descentralizado (grupos)
GROUPS = {
    "A": ["B"],      # A é líder de B
    "C": ["D", "E"]  # C é líder de D e E
}