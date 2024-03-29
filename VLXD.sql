USE [VLXD]
GO
/****** Object:  Table [dbo].[ACCOUNT]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ACCOUNT](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[ACCOUNT_NAME] [varchar](50) NULL,
	[PASSWORD] [varchar](500) NULL,
	[STATUS] [bigint] NULL,
	[AUTH_PROVIDER] [varchar](50) NULL,
 CONSTRAINT [PK_ACCOUNT] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UKjfarbv64d68y4tm9c9ms5fh7b] UNIQUE NONCLUSTERED 
(
	[ACCOUNT_NAME] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[AUTHORITIES]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AUTHORITIES](
	[ACCOUNT_ID] [bigint] NOT NULL,
	[ROLE_ID] [bigint] NOT NULL,
 CONSTRAINT [PK_AUTHORITIES] PRIMARY KEY CLUSTERED 
(
	[ACCOUNT_ID] ASC,
	[ROLE_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CUSTOMER]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CUSTOMER](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[NAME] [nvarchar](200) NULL,
	[PHONE] [varchar](20) NULL,
	[ADDRESS] [nvarchar](500) NULL,
	[CREATE_DATE] [datetime] NULL,
	[CREATE_BY] [nvarchar](100) NULL,
	[MODIFY_DATE] [datetime] NULL,
	[UPDATE_BY] [nvarchar](100) NULL,
	[STATUS] [bigint] NULL,
	[VILLAGE_ID] [bigint] NULL,
 CONSTRAINT [PK_CUSTOMER] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[HDN]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HDN](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[ACCOUNT_ID] [bigint] NULL,
	[SUPPLIER_ID] [bigint] NULL,
	[DATE_ADDED] [datetime] NULL,
	[CREATE_DATE] [datetime] NULL,
	[CREATE_BY] [nvarchar](100) NULL,
	[MODIFY_DATE] [datetime] NULL,
	[UPDATE_BY] [nvarchar](100) NULL,
	[STATUS] [bigint] NULL,
	[OWE] [float] NULL,
	[TOTAL_MONEY] [float] NULL,
	[PAY] [float] NULL,
	[TOTAL_BILL] [varchar](50) NULL,
 CONSTRAINT [PK_HDN] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[HDN_CT]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HDN_CT](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[PRODUCT_ID] [bigint] NULL,
	[HDN_ID] [bigint] NULL,
	[QUANTITY] [float] NULL,
	[PRICE] [float] NULL,
	[NOTE] [nvarchar](500) NULL,
	[CREATE_DATE] [datetime] NULL,
	[CREATE_BY] [nvarchar](100) NULL,
	[MODIFY_DATE] [datetime] NULL,
	[UPDATE_BY] [nvarchar](100) NULL,
	[STATUS] [bigint] NULL,
 CONSTRAINT [PK_HDN_CT] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HDN_CT_TON]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HDN_CT_TON](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[PRODUCT_ID] [bigint] NULL,
	[HDN_ID] [bigint] NULL,
	[WIDTH] [varchar](50) NULL,
	[HEIGHT] [varchar](50) NULL,
	[QUANTITY] [float] NULL,
	[NUMBER_M2] [varchar](50) NULL,
	[PRICE] [float] NULL,
	[NOTE] [nvarchar](500) NULL,
	[CREATE_DATE] [datetime] NULL,
	[CREATE_BY] [nvarchar](100) NULL,
	[MODIFY_DATE] [datetime] NULL,
	[UPDATE_BY] [nvarchar](100) NULL,
	[STATUS] [bigint] NULL,
 CONSTRAINT [PK_HDN_CT_TON] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[HDX]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HDX](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[CUSTOMER_ID] [bigint] NULL,
	[RELEASE_DATE] [datetime] NULL,
	[OWE] [float] NULL,
	[CREATE_DATE] [datetime] NULL,
	[CREATE_BY] [nvarchar](100) NULL,
	[MODIFY_DATE] [datetime] NULL,
	[UPDATE_BY] [nvarchar](100) NULL,
	[STATUS] [bigint] NULL,
	[CODE] [varchar](100) NULL,
	[TOTAL_MONEY] [float] NULL,
	[PAY] [float] NULL,
	[TOTAL_BILL] [varchar](50) NULL,
 CONSTRAINT [PK_HDX] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[HDX_CT]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HDX_CT](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[PRODUCT_ID] [bigint] NULL,
	[HDX_ID] [bigint] NULL,
	[QUANTITY] [float] NULL,
	[PRICE] [float] NULL,
	[NOTE] [nvarchar](500) NULL,
	[CREATE_DATE] [datetime] NULL,
	[CREATE_BY] [nvarchar](100) NULL,
	[MODIFY_DATE] [datetime] NULL,
	[UPDATE_BY] [nvarchar](100) NULL,
	[STATUS] [bigint] NULL,
 CONSTRAINT [PK_HDX_CT_1] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HDX_CT_TON]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HDX_CT_TON](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[PRODUCT_ID] [bigint] NULL,
	[HDX_ID] [bigint] NULL,
	[WIDTH] [varchar](50) NULL,
	[HEIGHT] [varchar](50) NULL,
	[QUANTITY] [float] NULL,
	[NUMBER_M2] [varchar](50) NULL,
	[PRICE] [float] NULL,
	[NOTE] [nvarchar](500) NULL,
	[CREATE_DATE] [datetime] NULL,
	[CREATE_BY] [nvarchar](100) NULL,
	[MODIFY_DATE] [datetime] NULL,
	[UPDATE_BY] [nvarchar](100) NULL,
	[STATUS] [bigint] NULL,
 CONSTRAINT [PK_HDX_CT_TON] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PAY]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PAY](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[ACCOUNT_ID] [bigint] NULL,
	[CUSTOMER_ID] [bigint] NULL,
	[HDX_ID] [bigint] NULL,
	[PAY_DAY] [datetime] NULL,
	[PAY_AMOUNT] [float] NULL,
	[TOTAL_MONEY_HDX] [float] NULL,
	[CREATE_DATE] [datetime] NULL,
	[CREATE_BY] [nvarchar](100) NULL,
	[MODIFY_DATE] [datetime] NULL,
	[UPDATE_BY] [nvarchar](100) NULL,
	[STATUS] [bigint] NULL,
 CONSTRAINT [PK_PAY] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PRODUCT]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PRODUCT](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[NAME] [nvarchar](200) NULL,
	[PRICE] [float] NULL,
	[QUANTITY] [float] NULL,
	[IMAGE] [nvarchar](200) NULL,
	[UNIT_ID] [bigint] NULL,
	[TYPE_ID] [bigint] NULL,
	[CREATE_DATE] [datetime] NULL,
	[CREATE_BY] [nvarchar](100) NULL,
	[MODIFY_DATE] [datetime] NULL,
	[UPDATE_BY] [nvarchar](100) NULL,
	[STATUS] [bigint] NULL,
 CONSTRAINT [PK_PRODUCT] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PRODUCT_TYPE]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PRODUCT_TYPE](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[TYPE_NAME] [nvarchar](100) NULL,
	[CREATE_DATE] [datetime] NULL,
	[CREATE_BY] [nvarchar](100) NULL,
	[MODIFY_DATE] [datetime] NULL,
	[UPDATE_BY] [nvarchar](100) NULL,
	[STATUS] [bigint] NULL,
 CONSTRAINT [PK_PRODUCT_TYPE] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ROLES]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ROLES](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[ROLE] [varchar](100) NULL,
 CONSTRAINT [PK_ROLES] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[STATUS]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[STATUS](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NULL,
 CONSTRAINT [PK_STATUS] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SUPPLIER]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[SUPPLIER](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[NAME] [nvarchar](500) NULL,
	[PHONE] [varchar](50) NULL,
	[ADDRESS] [nvarchar](500) NULL,
	[CREATE_DATE] [datetime] NULL,
	[CREATE_BY] [nvarchar](100) NULL,
	[MODIFY_DATE] [datetime] NULL,
	[UPDATE_BY] [nvarchar](100) NULL,
	[STATUS] [bigint] NOT NULL,
 CONSTRAINT [PK_SUPPLIER] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UNIT]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UNIT](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[UNIT_NAME] [nvarchar](200) NULL,
	[CREATE_DATE] [datetime] NULL,
	[CREATE_BY] [nvarchar](100) NULL,
	[MODIFY_DATE] [datetime] NULL,
	[UPDATE_BY] [nvarchar](100) NULL,
	[STATUS] [bigint] NULL,
 CONSTRAINT [PK_CATEGORY] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[VILLAGE]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[VILLAGE](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[VILLAGE_NAME] [nvarchar](200) NULL,
	[CREATE_DATE] [datetime] NULL,
	[CREATE_BY] [nvarchar](50) NULL,
	[MODIFY_DATE] [datetime] NULL,
	[UPDATE_BY] [nvarchar](50) NULL,
	[STATUS] [bigint] NULL,
 CONSTRAINT [PK_VILLAGE] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[AUTHORITIES]  WITH CHECK ADD  CONSTRAINT [FK_AUTHORITIES_ACCOUNT] FOREIGN KEY([ACCOUNT_ID])
REFERENCES [dbo].[ACCOUNT] ([ID])
GO
ALTER TABLE [dbo].[AUTHORITIES] CHECK CONSTRAINT [FK_AUTHORITIES_ACCOUNT]
GO
ALTER TABLE [dbo].[AUTHORITIES]  WITH CHECK ADD  CONSTRAINT [FK_AUTHORITIES_ROLES] FOREIGN KEY([ROLE_ID])
REFERENCES [dbo].[ROLES] ([ID])
GO
ALTER TABLE [dbo].[AUTHORITIES] CHECK CONSTRAINT [FK_AUTHORITIES_ROLES]
GO
ALTER TABLE [dbo].[AUTHORITIES]  WITH CHECK ADD  CONSTRAINT [FK7kj74d912rytfp5su91jfv0tg] FOREIGN KEY([ROLE_ID])
REFERENCES [dbo].[ROLES] ([ID])
GO
ALTER TABLE [dbo].[AUTHORITIES] CHECK CONSTRAINT [FK7kj74d912rytfp5su91jfv0tg]
GO
ALTER TABLE [dbo].[AUTHORITIES]  WITH CHECK ADD  CONSTRAINT [FKdt21kciy50c5sb1peuj5h6yu] FOREIGN KEY([ACCOUNT_ID])
REFERENCES [dbo].[ACCOUNT] ([ID])
GO
ALTER TABLE [dbo].[AUTHORITIES] CHECK CONSTRAINT [FKdt21kciy50c5sb1peuj5h6yu]
GO
ALTER TABLE [dbo].[CUSTOMER]  WITH CHECK ADD  CONSTRAINT [FK_CUSTOMER_VILLAGE] FOREIGN KEY([VILLAGE_ID])
REFERENCES [dbo].[VILLAGE] ([ID])
GO
ALTER TABLE [dbo].[CUSTOMER] CHECK CONSTRAINT [FK_CUSTOMER_VILLAGE]
GO
ALTER TABLE [dbo].[HDN]  WITH CHECK ADD  CONSTRAINT [FK_HDN_ACCOUNT] FOREIGN KEY([ACCOUNT_ID])
REFERENCES [dbo].[ACCOUNT] ([ID])
GO
ALTER TABLE [dbo].[HDN] CHECK CONSTRAINT [FK_HDN_ACCOUNT]
GO
ALTER TABLE [dbo].[HDN]  WITH CHECK ADD  CONSTRAINT [FK_HDN_SUPPLIER] FOREIGN KEY([SUPPLIER_ID])
REFERENCES [dbo].[SUPPLIER] ([ID])
GO
ALTER TABLE [dbo].[HDN] CHECK CONSTRAINT [FK_HDN_SUPPLIER]
GO
ALTER TABLE [dbo].[HDN_CT]  WITH CHECK ADD  CONSTRAINT [FK_HDN_CT_HDN] FOREIGN KEY([HDN_ID])
REFERENCES [dbo].[HDN] ([ID])
GO
ALTER TABLE [dbo].[HDN_CT] CHECK CONSTRAINT [FK_HDN_CT_HDN]
GO
ALTER TABLE [dbo].[HDN_CT]  WITH CHECK ADD  CONSTRAINT [FK_HDN_CT_PRODUCT1] FOREIGN KEY([PRODUCT_ID])
REFERENCES [dbo].[PRODUCT] ([ID])
GO
ALTER TABLE [dbo].[HDN_CT] CHECK CONSTRAINT [FK_HDN_CT_PRODUCT1]
GO
ALTER TABLE [dbo].[HDX]  WITH CHECK ADD  CONSTRAINT [FK_HDX_CUSTOMER] FOREIGN KEY([CUSTOMER_ID])
REFERENCES [dbo].[CUSTOMER] ([ID])
GO
ALTER TABLE [dbo].[HDX] CHECK CONSTRAINT [FK_HDX_CUSTOMER]
GO
ALTER TABLE [dbo].[HDX_CT]  WITH CHECK ADD  CONSTRAINT [FK_HDX_CT_HDX] FOREIGN KEY([HDX_ID])
REFERENCES [dbo].[HDX] ([ID])
GO
ALTER TABLE [dbo].[HDX_CT] CHECK CONSTRAINT [FK_HDX_CT_HDX]
GO
ALTER TABLE [dbo].[HDX_CT]  WITH CHECK ADD  CONSTRAINT [FK_HDX_CT_PRODUCT] FOREIGN KEY([PRODUCT_ID])
REFERENCES [dbo].[PRODUCT] ([ID])
GO
ALTER TABLE [dbo].[HDX_CT] CHECK CONSTRAINT [FK_HDX_CT_PRODUCT]
GO
ALTER TABLE [dbo].[HDX_CT_TON]  WITH CHECK ADD  CONSTRAINT [FK_HDX_CT_TON_HDX] FOREIGN KEY([HDX_ID])
REFERENCES [dbo].[HDX] ([ID])
GO
ALTER TABLE [dbo].[HDX_CT_TON] CHECK CONSTRAINT [FK_HDX_CT_TON_HDX]
GO
ALTER TABLE [dbo].[HDX_CT_TON]  WITH CHECK ADD  CONSTRAINT [FK_HDX_CT_TON_PRODUCT] FOREIGN KEY([PRODUCT_ID])
REFERENCES [dbo].[PRODUCT] ([ID])
GO
ALTER TABLE [dbo].[HDX_CT_TON] CHECK CONSTRAINT [FK_HDX_CT_TON_PRODUCT]
GO
ALTER TABLE [dbo].[PAY]  WITH CHECK ADD  CONSTRAINT [FK_PAY_ACCOUNT] FOREIGN KEY([ACCOUNT_ID])
REFERENCES [dbo].[ACCOUNT] ([ID])
GO
ALTER TABLE [dbo].[PAY] CHECK CONSTRAINT [FK_PAY_ACCOUNT]
GO
ALTER TABLE [dbo].[PAY]  WITH CHECK ADD  CONSTRAINT [FK_PAY_ACCOUNT1] FOREIGN KEY([ACCOUNT_ID])
REFERENCES [dbo].[ACCOUNT] ([ID])
GO
ALTER TABLE [dbo].[PAY] CHECK CONSTRAINT [FK_PAY_ACCOUNT1]
GO
ALTER TABLE [dbo].[PAY]  WITH CHECK ADD  CONSTRAINT [FK_PAY_CUSTOMER] FOREIGN KEY([CUSTOMER_ID])
REFERENCES [dbo].[CUSTOMER] ([ID])
GO
ALTER TABLE [dbo].[PAY] CHECK CONSTRAINT [FK_PAY_CUSTOMER]
GO
ALTER TABLE [dbo].[PAY]  WITH CHECK ADD  CONSTRAINT [FK_PAY_HDX] FOREIGN KEY([HDX_ID])
REFERENCES [dbo].[HDX] ([ID])
GO
ALTER TABLE [dbo].[PAY] CHECK CONSTRAINT [FK_PAY_HDX]
GO
ALTER TABLE [dbo].[PRODUCT]  WITH CHECK ADD  CONSTRAINT [FK_PRODUCT_CATEGORY] FOREIGN KEY([UNIT_ID])
REFERENCES [dbo].[UNIT] ([ID])
GO
ALTER TABLE [dbo].[PRODUCT] CHECK CONSTRAINT [FK_PRODUCT_CATEGORY]
GO
ALTER TABLE [dbo].[PRODUCT]  WITH CHECK ADD  CONSTRAINT [FK_PRODUCT_PRODUCT_TYPE] FOREIGN KEY([TYPE_ID])
REFERENCES [dbo].[PRODUCT_TYPE] ([ID])
GO
ALTER TABLE [dbo].[PRODUCT] CHECK CONSTRAINT [FK_PRODUCT_PRODUCT_TYPE]
GO
/****** Object:  StoredProcedure [dbo].[CustomerCount]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[CustomerCount]
@Text nvarchar(200),
@villageId int,
@pageNumber int,
@pageSize int,
@sort varchar(20)
AS
DECLARE @sql nvarchar(4000)
DECLARE @strPageSize nvarchar(20)
DECLARE @strPageNumber nvarchar(20)
DECLARE @strSortBy nvarchar(50) = ' ORDER BY '
DECLARE @strFilter nvarchar(500)
DECLARE @strFrom nvarchar(50)
DECLARE @strSkip nvarchar(20)
DECLARE @strVillageId nvarchar(20)
BEGIN
if(@pageNumber < 0 or @pageNumber is null)
set @strPageNumber = 0
else 
--set @strPageNumber = CAST(@pageNumber as nvarchar(20))
set @strPageNumber = CAST(((@pageNumber-1) * @pageSize) as nvarchar(20))
set @strPageSize = CAST(@pageSize as nvarchar(20))
set @strSkip = CAST(((@pageNumber -1) * @pageSize) as varchar(20))
 if(@sort is null)
 set @strSortBy = ' id'
 else 
 set @strSortBy = @strSortBy + 'c.'+@sort
 
 if(@Text is null)
 set @strFilter += ' '
 else 
 set @strFilter = @Text 

 set @strFrom = ' Customer'

  if(@villageId is null)
 set @strVillageId = 'null'
 else 
 set @strVillageId = CAST(@villageId as varchar(20)) 


 set @sql = 'select count(*) from ' + @strFrom  + ' c
 join VILLAGE v on v.id = c.village_id
  where c.status != 0 and (c.name like N''%'+@strFilter+'%'' or v.village_name like N''%'+@strFilter+'%'' ) and ' + @strVillageId +' is null or c.village_id = '+ @strVillageId    
--+ @strSortBy+ ' OFFSET ' + @strPageNumber +' rows fetch next ' + @strPageSize + '  rows only';
 --print @strFilter
 print @sql

 EXEC sp_executesql @sql
END;
GO
/****** Object:  StoredProcedure [dbo].[CustomerSearch]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- ================================================
CREATE PROCEDURE [dbo].[CustomerSearch]
@Text nvarchar(200),
@villageId int,
@pageNumber int,
@pageSize int,
@sort varchar(20)
AS
DECLARE @sql nvarchar(4000)
DECLARE @strPageSize nvarchar(20)
DECLARE @strPageNumber nvarchar(20)
DECLARE @strSortBy nvarchar(50) = ' ORDER BY '
DECLARE @strFilter nvarchar(500)
DECLARE @strFrom nvarchar(50)
DECLARE @strSkip nvarchar(20)
DECLARE @strVillageId nvarchar(20)
BEGIN
if(@pageNumber < 0 or @pageNumber is null)
set @strPageNumber = 0
else 
--set @strPageNumber = CAST(@pageNumber as nvarchar(20))
set @strPageNumber = CAST(((@pageNumber) * @pageSize) as nvarchar(20))
set @strPageSize = CAST(@pageSize as nvarchar(20))
set @strSkip = CAST(((@pageNumber -1) * @pageSize) as varchar(20))
 if(@sort is null)
 set @strSortBy = ' id'
 else 
 set @strSortBy = @strSortBy + 'c.'+@sort +' desc'
 
 if(@Text is null)
 set @strFilter += ''
 else 
 set @strFilter = @Text 
  if(@villageId is null)
 set @strVillageId = 'null'
 else 
 set @strVillageId = CAST(@villageId as varchar(20)) 

 set @strFrom = ' Customer'

 set @sql = 'select c.* from ' + @strFrom  + ' c
inner join VILLAGE v on v.id = c.village_id
  where c.status != 0 and (c.name like N''%'+@strFilter+'%'' or v.village_name like N''%'+@strFilter+'%'' ) and (' + @strVillageId +' is null or c.village_id = '+ @strVillageId+')'
+ @strSortBy+ ' OFFSET ' + @strPageNumber +' rows fetch next ' + @strPageSize + '  rows only';
 --print @strFilter
 print @sql

 EXEC sp_executesql @sql
END;
GO
/****** Object:  StoredProcedure [dbo].[ProductSearch]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[ProductSearch]
	@text nvarchar(200),
	@type int,
	@page int,
	@size int,
	@sort nvarchar(50)
AS
DECLARE @sql nvarchar(4000)
DECLARE @strType nvarchar(100)
DECLARE @strPage nvarchar(50)
DECLARE @strSize nvarchar(50)
DECLARE @strSort nvarchar(100)
DECLARE @strText nvarchar(200) 
BEGIN

if(@text is null)
set @strText = ''
else
set @strText = @text
if(@type is null or @type = 0 or @type = '')
set @strType = 'null'
else 
set @strType = CAST(@type as nvarchar(100))
if(@size is null)
set @strSize = CAST(100 as nvarchar(100))
else 
set @strSize = CAST(@size as nvarchar(100))
if(@page is null or @page < 0)
set @strPage = 0 
else
set @strPage = CAST((@page * @size) as nvarchar(100))
if(@sort is null)
set @strSort = 'id'
else 
set @strSort = @sort

set @sql = 'select p.* from PRODUCT p 
inner join PRODUCT_TYPE pt on pt.id = p.TYPE_ID
where (p.name like N''%' +@strText+'%'') and ('+@strType+' is null or p.type_id = '+@strType + ') and p.status != 0
 ORDER BY p.'+ @strSort + ' desc OFFSET '+@strPage + ' rows fetch next ' + @strSize + ' rows only'
print @sql

Exec sp_executesql @sql

END
GO
/****** Object:  StoredProcedure [dbo].[ProductSearchCount]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[ProductSearchCount]
	@text nvarchar(200),
	@type int,
	@page int,
	@size int,
	@sort nvarchar(50)
AS
DECLARE @sql nvarchar(4000)
DECLARE @strType nvarchar(100)
DECLARE @strPage nvarchar(50)
DECLARE @strSize nvarchar(50)
DECLARE @strSort nvarchar(100)
DECLARE @strText nvarchar(200) 
BEGIN

if(@text is null)
set @strText = ''
else
set @strText = @text
if(@type is null or @type = 0 or @type = '')
set @strType = 'null'
else 
set @strType = CAST(@type as nvarchar(100))
if(@size is null)
set @strSize = CAST(100 as nvarchar(100))
else 
set @strSize = CAST(@size as nvarchar(100))
if(@page is null or @page < 0)
set @strPage = 0 
else
set @strPage = CAST((@page * @size) as nvarchar(100))
if(@sort is null)
set @strSort = 'id'
else 
set @strSort = @sort

set @sql = 'select count(*) from PRODUCT p 
inner join PRODUCT_TYPE pt on pt.id = p.TYPE_ID
where (p.name like N''%' +@strText+'%'') and ('+@strType+' is null or p.type_id = '+@strType + ') and p.status != 0'
-- ORDER BY p.'+ @strSort + ' desc OFFSET '+@strPage + ' rows fetch next ' + @strSize + ' rows only'
print @sql

Exec sp_executesql @sql

END
GO
/****** Object:  StoredProcedure [dbo].[SearchCountHdx]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SearchCountHdx]
@text nvarchar(max),
@status int,
@page int,
@size int,
@sort nvarchar(200)
AS
DECLARE @sql nvarchar(4000)
DECLARE @strPage nvarchar(50)
DECLARE @strSize nvarchar(50)
DECLARE @strSort nvarchar(4000) = ' ORDER BY '
DECLARE @strStatus nvarchar(4000)
DECLARE @strFilter nvarchar(1000)

BEGIN
	if(@page is null)
	set @strPage = CAST( 0 as nvarchar(20))
	else 
	set @strPage = CAST((@page * @size) as nvarchar(50))
	if(@size is null or @size = '')
	set @strSize = '1000'
	else
	set @strSize = CAST(@size as nvarchar(50))
	if(@status is null)
	set @strStatus = ' 1,2 '
	else 
	set @strStatus = CAST(@status as nvarchar(20))
	if(@sort is null)
	set @strSort += ' h.id desc '
	else 
	set @strSort += 'h.'+ @sort +' DESC '
	--if(@text is null)
--	set @strFilter += ''
--	else
--	set @strFilter += @text
	 
	 set @sql= 'select count(*) from HDX h 
	 join CUSTOMER c on c.id = h.CUSTOMER_ID
	 join VILLAGE v on v.id = c.VILLAGE_ID
	 where (N'''+ @text+''' is null or CAST(h.id as nvarchar(50)) = N''' + @text + ''' or c.NAME like N''%' + @text +'%'' 
	 or v.VILLAGE_NAME like N''%' + @text +'%'') and h.status != 0 and h.status in (' + @strStatus +') '
	 -- + @strSort +   
	 --' OFFSET ' + @strPage + ' rows fetch next ' + @strSize + ' rows only';
	 print @sql;
	 print @sql;
	 EXEC sp_executesql @sql;

END

GO
/****** Object:  StoredProcedure [dbo].[SearchHdx]    Script Date: 8/3/2022 8:43:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SearchHdx]
@text nvarchar(max),
@status int,
@page int,
@size int,
@sort nvarchar(200)
AS
DECLARE @sql nvarchar(4000)
DECLARE @strPage nvarchar(50)
DECLARE @strSize nvarchar(50)
DECLARE @strSort nvarchar(4000) = ' ORDER BY '
DECLARE @strStatus nvarchar(4000)
DECLARE @strFilter nvarchar(1000)

BEGIN
	if(@page is null)
	set @strPage = CAST( 0 as nvarchar(20))
	else 
	set @strPage = CAST((@page * @size) as nvarchar(50))
	if(@size is null or @size = '')
	set @strSize = '1000'
	else
	set @strSize = CAST(@size as nvarchar(50))
	if(@status is null)
	set @strStatus = ' 1,2 '
	else 
	set @strStatus = CAST(@status as nvarchar(20))
	if(@sort is null or @sort = '')
	set @strSort += ' h.id desc '
	else 
	set @strSort += 'h.'+ @sort +' DESC '
	--if(@text is null)
--	set @strFilter += ''
--	else
--	set @strFilter += @text
	 
	 set @sql= 'select h.* from HDX h 
	 join CUSTOMER c on c.id = h.CUSTOMER_ID
	 join VILLAGE v on v.id = c.VILLAGE_ID
	 where (N'''+ @text+''' is null or CAST(h.id as nvarchar(50)) = N''' + @text + ''' or c.NAME like N''%' + @text +'%'' 
	 or v.VILLAGE_NAME like N''%' + @text +'%'') and h.status != 0 and h.status in (' + @strStatus +') ' + @strSort +   
	 ' OFFSET ' + @strPage + ' rows fetch next ' + @strSize + ' rows only';
	 print @sql;
	 EXEC sp_executesql @sql;

END

GO
