This is the full specification of the Mini-Triangle language as given in the book, "Programming Language Processors in Java".

# Syntax

## Terminals

```
begin end while do let in if then else var const 
; : , < > = \ := ~ ( ) + - * / 

```

## Grammar

### Concrete Syntax Rules

The non-terminal are:

```
Program (start symbol)

Command single-Command
Expression primary-Expression
V-name
Declaration single-Declaration
Type-denoter
Operator Identifier
Integer-Literal

```
The grammar in BNF is:

```
Program ::= single-Command

Command ::= single-Command
          | Command ";" single-Command

single-Command ::= V-name := Expression
                | Identifier "(" Expression ")"
                | "if" Expression "then" single-Command "else" single-Command
                | "while" Expression "do" single-Command
                | "let" Declaration "in" single-Command
                | "begin" Command "end"

Expression ::= primary-Expression
            | Expression Operator primary-Expression

primary-Expression ::= Integer-Literal
                    | V-name
                    | Operator primary-Expression
                    | "(" Expression ")"

V-name ::= Identifier

Declaration ::= single-Declaration
              | Declaration ";" single-Declaration

single-Declaration ::= "const" Identifier "~" Expression
                    | "var" Identifier ":" Type-denoter

Type-denoter ::= Identifier

Operator ::= + | - | * | / | < | > | = | \

Identifier ::= Letter | Identifier Letter | Identifier Digit

Integer-Literal ::= Digit | Integer-Literal Digit

Comment ::= ! Graphic* eol

```

### Abstract Syntax Rules

The non-terminals are:

```
Program (start symbol)
Command
Expression
Declaration
V-name
Type-Denoter

```

The grammar is:

```
Program ::=  Command        ; Program

Command ::=  V-name ":=" Expression   ; AssignCommand
          |  Identifier "(" Expression ")" ; CallCommand
          |  Command ";" Command ; SequentialCommand
          |  "if" Expression "then" Command "else" Command ; IfCommand
          |  "while" Expression "do" Command ; WhileCommand
          |  "let" Declaration "in" Command ; LetCommand

Expression ::= Integer-Literal ; Integer-Expression
            |  V-name ; VnameExpression
            |  Operator Expression ; UnaryExpression
            |  Expression Operator Expression ; BinaryExpression

V-name ::= Identifier  ; SimpleVname

Declaration ::= "const" Identifier "~" Expression ; ConstDeclaration
              |  "var" Identifier ":" Type-denoter ; VarDeclaration
              |   Declaration ";" Declaration ; SequentialDeclaration

Type-denoter ::= Identifier ; SimpleTypeDenoter

```

### Final Transformed Grammar

After carrying out grammar transformations, specifically Left Factorisation, Left Recursion Elimination, and Subsitution, we get the final grammar as:

```
Program ::= single-Command

Command ::= single-Command (";" single-Command)*

single-Command ::= Identifier (":=" Expression | "(" Expression ")")
                  | "if" Expression "then" single-Command "else" single-Command
                  | "while" Expression "do" single-Command
                  | "let" Declaration "in" single-Command
                  | "begin" Command "end"

Expression ::= primary-Expression (Operator primary-Expression)*

primary-Expression ::= Integer-Literal 
                    | Identifier (epsilon | "(" argument? ")")
                    | Operator primary-Expression
                    | "(" Expression ")"

argument ::= call-argument ("." call-argument)*

call-argument ::= expression

Declaration ::= single-Declaration (";" single-Declaration)*

single-Declaration ::= "const" Identifier "~" Expression
                | "var" Identifier ":" Type-denoter
                | "func" Identifier "(" param? ")" ":" Type-Denoter "~" Expression

params ::= param ("," param)* 

param ::= vname ":" Type-Denoter

Type-denoter ::= Identifier

(The rest of the grammar i.e., the lexical grammar, remains the same)

```

The final lexical grammar is:

```
Token ::= Letter (Letter | Digit)* ; Identifier
        | Digit Digit*  ; Integer-Literal 
        | + | - | * | / | < | > | = | \ ; Operator
        | ; | : (= | epsilon) | ~ | ( | ) | eot ; subset of keywords (the rest culled from the identifiers)

Separator ::= ! Graphic* eol | space | eol

```

# Semantics

A command C is executed to update variables.
  - An AssignCommand V := E is executed as follows: first the expression E is evaluated to yield a value v, and then v is assigned to the variable-or-value V.
  - A CallCommand I(E) is executed as follows: first the expression E is evaluated to yield a value v, and then the procedure bound to I is called with v as its
    argument.
  - A SequentialCommand C1 ; C2 is executed as follows: first C1 is executed, and then C2 is executed.
  - An IfCommand if E then C1 else C2 is executed as follows: first the expression E is evaluated to yield a value v. If v is true then the command C1 is executed,
    otherwise the command C2 is executed.
  - A WhileCommand While E do C is executed as follows: first the expression E is evaluated to yield a value v. If v is true then the command C is executed, and then the
    WhileCommand is executed again. If v is false, then execution of the WhileCommand is complete.
  - A LetCommand let D in C is executed as follows: first the declaration D is elaborated to produce bindings b. C is then executed in the environment of the LetCommand
    overlaid by the bindings b. The bindings b have no effect outside the LetCommand.

An expression E is evaluated to yield a value.
  - The IntegerExpression IL yields the value of the integer literal IL.
  - The VnameExpression V yields the value of the value-or-variable-name V.
  - The UnaryExpression O E yields the value obtained by applying the unary operator O to the value yielded by the expression E.
  - The BinaryExpression E1 O E2 yields the value obtained by applying the binary operator O to the values yielded by evaluating the expressions E1 and E2. The order of
    evaluation of E1 and E2 is irrelevant since expressions have no side-effects in Mini-Triangle.

A value-or-variable-name V may be identified to either yield a value or to assign a value to a variable, depending on the context:
  - A SimpleValue I yields a value as follows: if I is bound to a value, that value is yielded otherwise I must be bound to a variable in which case the value
    associated with the variable is yielded instead.
  - A SimpleValue I is assigned a value as follows: If I is bound to a variable, it updates that variable to have the value associated with I. The case where I is a pure value itself
    is disallowed.

A declaration D is elaborated to produce bindings. It may also have the side-effect of allocating variables.
  - A ConstDeclaration const D ~ E is elaborated as follows: first the expression E is evaluated to a value v, and then I is bound to v.
  - A VarDeclaration var D: T is elaborated as follows: I is bound to a newly-allocated variable of type T, with an undefined initial value. This variable will be 
    deallocated upon exit from the block.
  - A SequentialDeclaration D1 ; D2 is elaborated as follows: elaborate D1 producing bindings b1, and then elaborate D2 producing bindings b2 in the environment overlaid by b1.