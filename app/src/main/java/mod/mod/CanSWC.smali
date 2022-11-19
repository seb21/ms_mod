.class public Lmod/mod/CanSWC;
.super Ljava/lang/Object;
.source "CanSWC.java"


# static fields
.field public static action1:I

.field public static code1:I

.field public static count:Z

.field public static dbc:Z

.field public static doNotReact:Z

.field public static doNothing:Ljava/lang/Boolean;

.field public static done:Z

.field public static keyPressed:Z

.field public static lastK:I

.field public static lastKey:I

.field public static nextPress:I

.field public static secAction:I

.field public static secCode:I

.field public static sent:Z

.field public static sourceSelect:I

.field public static timer:Landroid/os/CountDownTimer;

.field public static timerWork:Ljava/lang/Boolean;

.field public static what:I


# direct methods
.method static constructor <clinit>()V
    .registers 2

    .prologue
    const/4 v1, 0x0

    .line 14
    sput v1, Lmod/mod/CanSWC;->sourceSelect:I

    .line 15
    sput-boolean v1, Lmod/mod/CanSWC;->keyPressed:Z

    .line 24
    sput-boolean v1, Lmod/mod/CanSWC;->done:Z

    .line 25
    sput-boolean v1, Lmod/mod/CanSWC;->doNotReact:Z

    .line 26
    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    sput-object v0, Lmod/mod/CanSWC;->doNothing:Ljava/lang/Boolean;

    .line 27
    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    sput-object v0, Lmod/mod/CanSWC;->timerWork:Ljava/lang/Boolean;

    .line 29
    sput-boolean v1, Lmod/mod/CanSWC;->count:Z

    .line 30
    sput-boolean v1, Lmod/mod/CanSWC;->sent:Z

    .line 31
    sput-boolean v1, Lmod/mod/CanSWC;->dbc:Z

    return-void
.end method

.method public constructor <init>()V
    .registers 1

    .prologue
    .line 13
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static keyFunction(III)I
    .registers 7
    .param p0, "code"    # I
    .param p1, "action"    # I
    .param p2, "what1"    # I

    .prologue
    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 36
    add-int/2addr p0, p2

    .line 37
    sput p2, Lmod/mod/CanSWC;->what:I

    .line 38
    sput p1, Lmod/mod/CanSWC;->action1:I

    .line 39
    sput p0, Lmod/mod/CanSWC;->code1:I

    .line 40
    sget-object v0, Lmod/mod/Start;->setApp:Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    if-eqz v0, :cond_18

    .line 41
    sub-int v0, p0, p2

    invoke-static {v0, p2, p1}, Lmod/mod/Start;->codeIntent(III)V

    .line 42
    const/4 p0, -0x1

    .line 86
    :cond_17
    :goto_17
    return p0

    .line 44
    :cond_18
    add-int/lit8 v0, p2, 0x7

    if-eq p0, v0, :cond_72

    move v3, v1

    :goto_1d
    add-int/lit8 v0, p2, 0x8

    if-eq p0, v0, :cond_74

    move v0, v1

    :goto_22
    and-int/2addr v0, v3

    if-eqz v0, :cond_68

    .line 46
    sget-boolean v0, Lmod/mod/CanSWC;->doNotReact:Z

    if-nez v0, :cond_88

    .line 47
    sget-boolean v0, Lmod/mod/CanSWC;->done:Z

    if-nez v0, :cond_7c

    .line 48
    sget-object v0, Lmod/mod/CanSWC;->timerWork:Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    if-nez v0, :cond_3b

    .line 49
    sput-boolean v2, Lmod/mod/CanSWC;->sent:Z

    .line 50
    const/16 v0, 0x63

    sput v0, Lmod/mod/CanSWC;->nextPress:I

    .line 52
    :cond_3b
    sget-object v0, Lmod/mod/CanSWC;->timerWork:Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v3

    sget v0, Lmod/mod/CanSWC;->action1:I

    if-nez v0, :cond_76

    move v0, v1

    :goto_46
    and-int/2addr v0, v3

    if-eqz v0, :cond_4b

    .line 53
    sput-boolean v1, Lmod/mod/CanSWC;->count:Z

    .line 55
    :cond_4b
    sget-object v0, Lmod/mod/CanSWC;->timerWork:Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    if-nez v0, :cond_78

    move v3, v1

    :goto_54
    if-nez p1, :cond_7a

    move v0, v1

    :goto_57
    and-int/2addr v0, v3

    if-eqz v0, :cond_65

    .line 56
    sput-boolean v2, Lmod/mod/CanSWC;->count:Z

    .line 57
    sget v0, Lmod/mod/CanSWC;->code1:I

    sput v0, Lmod/mod/CanSWC;->nextPress:I

    .line 58
    sput-boolean v1, Lmod/mod/CanSWC;->keyPressed:Z

    .line 59
    invoke-static {}, Lmod/mod/Start;->keyCIntent()V

    .line 61
    :cond_65
    sput v1, Lmod/mod/CanSWC;->action1:I

    .line 62
    const/4 p0, -0x1

    .line 77
    :cond_68
    :goto_68
    const/4 v0, -0x1

    if-eq p0, v0, :cond_17

    .line 78
    const/16 v0, 0x64

    if-ne p2, v0, :cond_90

    .line 79
    add-int/lit8 p0, p0, 0x78

    goto :goto_17

    :cond_72
    move v3, v2

    .line 44
    goto :goto_1d

    :cond_74
    move v0, v2

    goto :goto_22

    :cond_76
    move v0, v2

    .line 52
    goto :goto_46

    :cond_78
    move v3, v2

    .line 55
    goto :goto_54

    :cond_7a
    move v0, v2

    goto :goto_57

    .line 64
    :cond_7c
    sget-boolean v0, Lmod/mod/CanSWC;->done:Z

    sget-boolean v1, Lmod/mod/CanSWC;->sent:Z

    and-int/2addr v0, v1

    if-eqz v0, :cond_68

    .line 65
    sput-boolean v2, Lmod/mod/CanSWC;->done:Z

    .line 66
    sput v2, Lmod/mod/CanSWC;->action1:I

    goto :goto_68

    .line 70
    :cond_88
    sput-boolean v2, Lmod/mod/CanSWC;->doNotReact:Z

    .line 71
    if-ne p1, v1, :cond_68

    .line 72
    sput v1, Lmod/mod/CanSWC;->action1:I

    .line 73
    const/4 p0, -0x1

    goto :goto_68

    .line 80
    :cond_90
    const/16 v0, 0xc8

    if-ne p2, v0, :cond_17

    .line 81
    add-int/lit16 p0, p0, -0xc8

    goto :goto_17
.end method
