package com.codewaves.codehighlight.languages
import com.codewaves.codehighlight.core.*
/*
Language = Perl
Author = Peter Leonov <gojpeg@yandex.ru>
Category = common
*/
/**
 * This function was automatically generated, avoid directly editing it if possible!
 * Origin highlight.js/src/languages/perl.js MD5 <6114457e24e7a45352fd358ceeb4f8b6>
 */
internal fun perl(): Mode {
    var PERL_KEYWORDS = "getpwent getservent quotemeta msgrcv scalar kill dbmclose undef lc ma syswrite tr send umask sysopen shmwrite vec qx utime local oct semctl localtime readpipe do return format read sprintf dbmopen pop getpgrp not getpwnam rewinddir qqfileno qw endprotoent wait sethostent bless s|0 opendir continue each sleep endgrent shutdown dump chomp connect getsockname die socketpair close flock exists index shmgetsub for endpwent redo lstat msgctl setpgrp abs exit select print ref gethostbyaddr unshift fcntl syscall goto getnetbyaddr join gmtime symlink semget splice x|0 getpeername recv log setsockopt cos last reverse gethostbyname getgrnam study formline endhostent times chop length gethostent getnetent pack getprotoent getservbyname rand mkdir pos chmod y|0 substr endnetent printf next open msgsnd readdir use unlink getsockopt getpriority rindex wantarray hex system getservbyport endservent int chr untie rmdir prototype tell listen fork shmread ucfirst setprotoent else sysseek link getgrgid shmctl waitpid unpack getnetbyname reset chdir grep split require caller lcfirst until warn while values shift telldir getpwuid my getprotobynumber delete and sort uc defined srand accept package seekdir getprotobyname semop our rename seek if q|0 chroot sysread setpwent no crypt getc chown sqrt write setnetent setpriority foreach tie sin msgget map stat getlogin unless elsif truncate exec keys glob tied closedirioctl socket readlink eval xor readline binmode setservent eof ord bind alarm pipe atan2 getgrent exp time push setgrent gt lt or ne m|0 break given say state when"
    var SUBST = Mode(
        className = "subst",
        begin = "[\$@]\\{",
        end = "\\}",
        keywords = keywords(PERL_KEYWORDS)
    )
    var METHOD = Mode(
        begin = "->{",
        end = "}"
        // contains defined later
    )
    var VAR = Mode(
        variants = listOf(
            Mode(begin =
                    """\${'$'}\d"""),
            Mode(begin =
                    """[\${'$'}%@](\^\w\b|#\w+(::\w+)*|{\w+}|\w+(::\w*)*)"""),
            Mode(
                begin =
                    """[\${'$'}%@][^\s\w{]""",
                relevance = 0
            )
        )
    )
    var STRING_CONTAINS = listOf(hljs.BACKSLASH_ESCAPE, SUBST, VAR)
    var PERL_DEFAULT_CONTAINS = listOf(
        VAR,
        hljs.HASH_COMMENT_MODE,
        hljs.COMMENT(
            "^\\=\\w",
            "\\=cut",
            Mode(
                endsWithParent = true
            )
        ),
        METHOD,
        Mode(
            className = "string",
            contains = STRING_CONTAINS,
            variants = listOf(
                Mode(
                    begin = "q[qwxr]?\\s*\\(",
                    end = "\\)",
                    relevance = 5
                ),
                Mode(
                    begin = "q[qwxr]?\\s*\\[",
                    end = "\\]",
                    relevance = 5
                ),
                Mode(
                    begin = "q[qwxr]?\\s*\\{",
                    end = "\\}",
                    relevance = 5
                ),
                Mode(
                    begin = "q[qwxr]?\\s*\\|",
                    end = "\\|",
                    relevance = 5
                ),
                Mode(
                    begin = "q[qwxr]?\\s*\\<",
                    end = "\\>",
                    relevance = 5
                ),
                Mode(
                    begin = "qw\\s+q",
                    end = "q",
                    relevance = 5
                ),
                Mode(
                    begin = "'",
                    end = "'",
                    contains = listOf(hljs.BACKSLASH_ESCAPE)
                ),
                Mode(
                    begin = "\"",
                    end = "\""
                ),
                Mode(
                    begin = "`",
                    end = "`",
                    contains = listOf(hljs.BACKSLASH_ESCAPE)
                ),
                Mode(
                    begin = "{\\w+}",
                    contains = listOf(),
                    relevance = 0
                ),
                Mode(
                    begin = "\-?\\w+\\s*\\=\\>",
                    contains = listOf(),
                    relevance = 0
                )
            )
        ),
        Mode(
            className = "number",
            begin = "(\\b0[0-7_]+)|(\\b0x[0-9a-fA-F_]+)|(\\b[1-9][0-9_]*(\\.[0-9_]+)?)|[0_]\\b",
            relevance = 0
        ),
        Mode(// regexp container
            begin = "(\\/\\/|\" + hljs.RE_STARTERS_RE + \"|\\b(split|return|print|reverse|grep)\\b)\\s*",
            keywords = keywords("split return print reverse grep"),
            relevance = 0,
            contains = listOf(
                hljs.HASH_COMMENT_MODE,
                Mode(
                    className = "regexp",
                    begin = "(s|tr|y)/(\\\\.|[^/))*/(\\\\.|[^/))*/[a-z]*",
                    relevance = 10
                ),
                Mode(
                    className = "regexp",
                    begin = "(m|qr)?/",
                    end = "/[a-z]*",
                    contains = listOf(hljs.BACKSLASH_ESCAPE),
                    relevance = 0 // allows empty "//" which is a common comment delimiter in other languages
                )
            )
        ),
        Mode(
            className = "function",
            beginKeywords = keywords("sub"),
            end = "(\\s*\\(.*?\\))?[;{]",
            excludeEnd = true,
            relevance = 5,
            contains = listOf(hljs.TITLE_MODE)
        ),
        Mode(
            begin = "-\\w\\b",
            relevance = 0
        ),
        Mode(
            begin = "^__DATA__\${'$'}",
            end = "^__END__\${'$'}",
            subLanguage = "mojolicious",
            contains = listOf(
                Mode(
                    begin = "^@@.*",
                    end = "\${'$'}",
                    className = "comment"
                )
            )
        )
    )
    SUBST.contains = PERL_DEFAULT_CONTAINS
    METHOD.contains = PERL_DEFAULT_CONTAINS
    return Mode(
        aliases = listOf("pl\", \"pm"),
        lexemes =
            """[\w\.]+""",
        keywords = keywords(PERL_KEYWORDS),
        contains = PERL_DEFAULT_CONTAINS
    )
}
